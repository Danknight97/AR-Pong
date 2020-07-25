package com.unity3d.player;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.LinkedList;

/* renamed from: com.unity3d.player.d */
public final class C0033d implements C0031b {
    /* renamed from: a */
    private static boolean m60a(PackageItemInfo packageItemInfo) {
        try {
            return packageItemInfo.metaData.getBoolean("unityplayer.SkipPermissionsDialog");
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a */
    public final void mo107a(Activity activity, final Runnable runnable) {
        String[] strArr;
        if (activity != null) {
            PackageManager packageManager = activity.getPackageManager();
            try {
                ActivityInfo activityInfo = packageManager.getActivityInfo(activity.getComponentName(), 128);
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(activity.getPackageName(), 128);
                if (m60a(activityInfo) || m60a(applicationInfo)) {
                    runnable.run();
                    return;
                }
            } catch (Exception e) {
            }
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(activity.getPackageName(), 4096);
                if (packageInfo.requestedPermissions == null) {
                    packageInfo.requestedPermissions = new String[0];
                }
                final LinkedList linkedList = new LinkedList();
                for (String str : packageInfo.requestedPermissions) {
                    try {
                        if (packageManager.getPermissionInfo(str, 128).protectionLevel == 1 && activity.checkCallingOrSelfPermission(str) != 0) {
                            linkedList.add(str);
                        }
                    } catch (NameNotFoundException e2) {
                        C0032c.Log(5, "Failed to get permission info for " + str + ", manifest likely missing custom permission declaration");
                        C0032c.Log(5, "Permission " + str + " ignored");
                    }
                }
                if (linkedList.isEmpty()) {
                    runnable.run();
                    return;
                }
                final FragmentManager fragmentManager = activity.getFragmentManager();
                C00341 r1 = new Fragment() {
                    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
                        if (i == 15881) {
                            int i2 = 0;
                            while (i2 < strArr.length && i2 < iArr.length) {
                                C0032c.Log(4, strArr[i2] + (iArr[i2] == 0 ? " granted" : " denied"));
                                i2++;
                            }
                            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                            beginTransaction.remove(this);
                            beginTransaction.commit();
                            runnable.run();
                        }
                    }

                    public final void onStart() {
                        super.onStart();
                        requestPermissions((String[]) linkedList.toArray(new String[0]), 15881);
                    }
                };
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.add(0, r1);
                beginTransaction.commit();
            } catch (Exception e3) {
                C0032c.Log(6, "Unable to query for permission: " + e3.getMessage());
            }
        }
    }
}
