/*
 * Copyright (C) 2014 The Android Open Source Project
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

package com.android.camera.processing;

import android.content.Context;

import com.android.camera.session.CaptureSession;

/**
 * An interface for tasks to be processed by a {@code ProcessingService}.
 */
public interface ProcessingTask {
    /**
     * The result returned by a {@code ProcessingTask}.
     */
    public class ProcessingResult {
        public final boolean mSuccess;
        public final CaptureSession mSession;

        /**
         * @param success whether the processing was successful.
         * @param session the capture session for the processed task.
         */
        public ProcessingResult(boolean success, CaptureSession session) {
            mSuccess = success;
            mSession = session;
        }
    }

    /**
     * Classes implementing this interface can be informed when a task is done
     * processing.
     */
    public interface ProcessingTaskDoneListener {
        /**
         * Called when a task is done processing.
         *
         * @param result the processing result.
         */
        public void onDone(ProcessingResult result);
    }

    /**
     * Processes the given task. This will be usually called by a service.
     *
     * @param context the caller {@code Context}
     * @param session the {@code CaptureSession}
     * @return the {@code ProcessResult} with the result of the processing
     */
    public ProcessingResult process(Context context, CaptureSession session);

    /**
     * @return the name of the task. It can be null to indicate that the task
     *         has no name.
     */
    public String getName();

    /** Sets a listener that is informed when this task is done processing. */
    public void setDoneListener(ProcessingTaskDoneListener listener);
}
