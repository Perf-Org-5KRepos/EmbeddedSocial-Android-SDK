/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *
 */

package com.microsoft.socialplus.server.model.content.replies;

import com.microsoft.socialplus.autorest.models.ReplyView;
import com.microsoft.rest.ServiceException;
import com.microsoft.rest.ServiceResponse;
import com.microsoft.socialplus.server.exception.NetworkRequestException;

import java.io.IOException;

public class GetReplyRequest extends GenericReplyRequest {
	public GetReplyRequest(String replyHandle) {
		super(replyHandle);
	}

	@Override
	public GetReplyResponse send() throws NetworkRequestException {
		ServiceResponse<ReplyView> serviceResponse;
		try {
			serviceResponse = REPLIES.getReply(replyHandle, authorization);
		} catch (ServiceException|IOException e) {
			throw new NetworkRequestException(e.getMessage());
		}
		checkResponseCode(serviceResponse);

		return new GetReplyResponse(
				new com.microsoft.socialplus.server.model.view.ReplyView(serviceResponse.getBody()));
	}
}
