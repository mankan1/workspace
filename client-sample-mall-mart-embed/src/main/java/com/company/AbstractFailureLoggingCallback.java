/**
 * Copyright (C) 2011 Qualcomm Retail Solutions, Inc. All rights reserved.
 *
 * This software is the confidential and proprietary information of Qualcomm Retail Solutions, Inc.
 *
 * The following sample code illustrates various aspects of the Gimbal SDK.
 *
 * The sample code herein is provided for your convenience, and has not been
 * tested or designed to work on any particular system configuration. It is
 * provided AS IS and your use of this sample code, whether as provided or with
 * any modification, is at your own risk. Neither Qualcomm Retail Solutions, Inc. nor any
 * affiliate takes any liability nor responsibility with respect to the sample
 * code, and disclaims all warranties, express and implied, including without
 * limitation warranties on merchantability, fitness for a specified purpose,
 * and against infringement.
 */
package com.company;

import android.util.Log;

import com.qualcommlabs.usercontext.Callback;

public abstract class AbstractFailureLoggingCallback<T> implements Callback<T> {

    private static final String TAG = AbstractFailureLoggingCallback.class.getSimpleName();

    private final MallMartPresenter mallMartPresenter;

    public AbstractFailureLoggingCallback(MallMartPresenter mallMartPresenter) {
        this.mallMartPresenter = mallMartPresenter;
    }

    @Override
    public void failure(int statusCode, String errorMessage) {
        Log.e(TAG, errorMessage);
        //        mallMartPresenter.updatePlaceEventText(errorMessage);
        mallMartPresenter.toastAndLogError(errorMessage);
    }

}
