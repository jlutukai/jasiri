package re.iprocu.data.utils.retry_policy

import re.iprocu.domain.utils.retry_policy.RetryPolicy

internal class RetryPolicyImpl(
    override val numOfRetries: Long = NO_NETWORK_REQUEST_RETRIES,
    override val delayMillis: Long = RETRY_DELAY_MS,
    override val delayFactor: Long = DELAY_FACTOR_MS
) : RetryPolicy {
    companion object {
        const val NO_NETWORK_REQUEST_RETRIES = 2L
        const val RETRY_DELAY_MS = 200L
        const val DELAY_FACTOR_MS = 2L
    }
}