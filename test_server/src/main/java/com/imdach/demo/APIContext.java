package com.imdach.demo;

import java.util.UUID;

/**
 * Created By Arthur Zhang at 2022/4/2
 */
public class APIContext  {

    /**
     * OAuth Token
     */
    private String accessToken;

    /**
     * Request Id
     */
    private String requestId;

    /**
     * Parameter to mask RequestId
     */
    private boolean maskRequestId;

    /**
     * Default Constructor
     */
    public APIContext() {
        super();
    }

    /**
     * APIContext, requestId is auto generated, calling setMaskRequestId(true)
     * will override the requestId getter to return null
     *
     * @param accessToken
     *            OAuthToken required for the call. OAuth token used by the REST
     *            API service. The token should be of the form 'Bearer xxxx..'.
     */
    public APIContext(String accessToken) {
        super();
        if (accessToken == null || accessToken.length() <= 0) {
            throw new IllegalArgumentException("AccessToken cannot be null");
        }
        this.accessToken = accessToken;
    }

    /**
     * APIContext
     *
     * @param accessToken
     *            OAuthToken required for the call. OAuth token used by the REST
     *            API service. The token should be of the form 'Bearer xxxx..'.
     * @param requestId
     *            Unique requestId required for the call. Idempotency id,
     *            Calling setMaskRequestId(true) will override the requestId
     *            getter to return null, which can be used by the client (null
     *            check) to forcibly not sent requestId in the API call.
     */
    public APIContext(String accessToken, String requestId) {
        this(accessToken);
        if (requestId == null || requestId.length() <= 0) {
            throw new IllegalArgumentException("RequestId cannot be null");
        }
        this.requestId = requestId;
    }

    /**
     * Returns the Access Token
     *
     * @return Access Token
     */
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * Returns the unique requestId set during creation, if not available and if
     * maskRequestId is set to false returns a generated one, else returns null.
     *
     * @return requestId
     */
    public String getRequestId() {
        String reqId = null;
        if (!maskRequestId) {
            if (requestId == null || requestId.length() <= 0) {
                requestId = UUID.randomUUID().toString();
            }
            reqId = requestId;
        }
        return reqId;
    }

    /**
     * @param maskRequestId
     *            the maskRequestId to set
     */
    public void setMaskRequestId(boolean maskRequestId) {
        this.maskRequestId = maskRequestId;
    }


}
