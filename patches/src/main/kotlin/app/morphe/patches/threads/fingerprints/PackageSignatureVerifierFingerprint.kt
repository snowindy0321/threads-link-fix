package app.morphe.patches.threads.fingerprints

import app.morphe.patcher.Fingerprint

/**
 * 匹配 Meta 底層套件簽名驗證器 (如 LX/5tI;->A04)。
 * 比對 Context 與目標套件名稱 (String) 的簽名。
 */
internal object PackageSignatureVerifierFingerprint : Fingerprint(
    returnType = "Z",
    parameters = listOf("Landroid/content/Context;", "Ljava/lang/String;"),
    strings = listOf(" not found by PackageManager.")
)
