package app.morphe.patches.threads.fingerprints

import app.morphe.patcher.Fingerprint

/**
 * 匹配 Meta 核心身分驗證器 AppIdentityVerifier (如 LX/7tz;->A02)。
 * 檢查套件的 SHA-256 憑證指紋是否包含在 Meta 官方公鑰白名單中。
 */
internal object AppIdentityVerifierFingerprint : Fingerprint(
    returnType = "Z",
    strings = listOf("*|all_packages|*")
)
