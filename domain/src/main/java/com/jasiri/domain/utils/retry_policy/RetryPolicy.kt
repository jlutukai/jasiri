package re.iprocu.domain.utils.retry_policy

interface RetryPolicy {
    val numOfRetries: Long
    val delayMillis: Long
    val delayFactor: Long
}