/*
 * Copyright 2013 SlimRom
 * Copyright 2015 AICP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.systemui.shortcuts;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ServiceManager;

import com.android.internal.statusbar.IStatusBarService;

public class ClearNotifications extends Activity  {

    protected IStatusBarService mBarService;
    protected int mCurrentUserId = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBarService = IStatusBarService.Stub.asInterface(
                ServiceManager.getService(Context.STATUS_BAR_SERVICE));
        mCurrentUserId = ActivityManager.getCurrentUser();
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            mBarService.onClearAllNotifications(mCurrentUserId);
        } catch (Exception ex) { }
        this.finish();
    }
}
