#
# checkroot.sh	Check to root filesystem.
#
# Version:	@(#)checkroot.sh  2.84  25-Jan-2002  miquels@cistron.nl
#

. /etc/default/functions
. /etc/default/rcS

#
# Set SULOGIN in /etc/default/rcS to yes if you want a sulogin to be spawned
# from this script *before anything else* with a timeout, like SCO does.
#
test "$SULOGIN" = yes && sulogin -t 30 $CONSOLE

#
# Ensure that bdflush (update) is running before any major I/O is
# performed (the following fsck is a good example of such activity :).
#
test -x /sbin/update && update

#
# Read /etc/fstab.
#
exec 9>&0 </etc/fstab
rootmode=rw
rootopts=rw
swap_on_md=no
devfs=
while read fs mnt type opts dump pass junk
do
	case "$fs" in
		""|\#*)
			continue;
			;;
		/dev/md*)
			# Swap on md device.
			test "$type" = swap && swap_on_md=yes
			;;
		/dev/*)
			;;
		*)
			# Might be a swapfile.
			test "$type" = swap && swap_on_md=yes
			;;
	esac
	test "$type" = devfs && devfs="$fs"
	test "$mnt" != / && continue
	rootopts="$opts"
	test "$pass" = 0 -o "$pass" = "" && ROOTFSCK=no
	case "$opts" in
		ro|ro,*|*,ro|*,ro,*)
			rootmode=ro
			;;
	esac
done
exec 0>&9 9>&-

#
# Activate the swap device(s) in /etc/fstab. This needs to be done
# before fsck, since fsck can be quite memory-hungry.
#
# We don't test whether we're running a 2.[0123].x kernel and md
# since that's plain too old.

if test "$DOSWAP" != no
then
	swaps="$(blkid -t TYPE=swap -o device 2>/dev/null)"
	if test -n "$swaps"
	then
		swapon $swaps 2>/dev/null
	else
		swapon -a 2> /dev/null
	fi
fi

#
# Check the root filesystem.
#
if test -f /fastboot || test "$ROOTFSCK" != yes
then
  test "$ROOTFSCK" = yes && echo "Fast boot, no filesystem check"
elif test ! -x /sbin/fsck -a ! -x /usr/sbin/fsck
then
  echo "/etc/init.d/checkroot.sh: no fsck"
else
  leds disk-1 slow
  #
  # Ensure that root is quiescent and read-only before fsck'ing.
  #
  mount -n -o remount,ro /
  if test $? = 0
  then
    if test -f /forcefsck
    then
	force="-f"
    else
	force=""
    fi
    if test "$FSCKFIX" = yes
    then
	fix="-y"
    else
	fix="-a"
    fi
    spinner="-C"
    case "$TERM" in
        dumb|network|unknown|"") spinner="" ;;
    esac
    test "$VERBOSE" != no && echo "Checking root filesystem..."
    fsck $spinner $force $fix /
    #
    # If there was a failure, drop into single-user mode.
    #
    # NOTE: "failure" is defined as exiting with a return code of
    # 2 or larger.  A return code of 1 indicates that filesystem
    # errors were corrected but that the boot may proceed.
    #
    if test "$?" -gt 1
    then
      # Surprise! Re-directing from a HERE document (as in
      # "cat << EOF") won't work, because the root is read-only.
      echo
      echo "fsck failed.  Please repair manually and reboot.  Please note"
      echo "that the root filesystem is currently mounted read-only.  To"
      echo "remount it read-write:"
      echo
      echo "   # mount -n -o remount,rw /"
      echo
      echo "CONTROL-D will exit from this shell and REBOOT the system."
      echo
      leds system panic
      leds beep -r 5
      # Start a single user shell on the console
      if single_user_ok
      then
	sulogin -t 600 $CONSOLE
	# if this exits with SIGALRM (which happens to be 142) the
	# timeout happened, do not, then, reboot!
	if test $? -ne 142
	then
	  reboot -f
	else
	  echo "/etc/init.d/checkroot.sh: sulogin timeout, continuing boot"
	fi
      else
	echo "/etc/init.d/checkroot.sh: fsck failed, continuing boot"
      fi
    fi
  else
    echo "*** ERROR!  Cannot fsck root fs because it is not mounted read-only!"
    echo
  fi
  leds disk-1 off
fi

#
#	If the root filesystem was not marked as read-only in /etc/fstab,
#	remount the rootfs rw but do not try to change mtab because it
#	is on a ro fs until the remount succeeded. Then clean up old mtabs
#	and finally write the new mtab.
#
mount -n -o remount,$rootopts /
if test "$rootmode" = rw
then
	if test ! -L /etc/mtab
	then
		rm -f /etc/mtab~ /etc/nologin
		: > /etc/mtab
	fi
	mount -f -o remount /
	mount -f /proc
	mount -f /sys
	test "$devfs" && grep -q '^devfs /dev' /proc/mounts && mount -f "$devfs"
fi

: exit 0
