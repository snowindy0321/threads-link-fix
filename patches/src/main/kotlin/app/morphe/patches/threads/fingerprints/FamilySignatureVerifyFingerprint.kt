package app.morphe.patches.threads.fingerprints

import app.morphe.patcher.Fingerprint

/**
 * 匹配 Meta FamilyIntentScope (如 LX/2Sh;) 中的套件簽名匹配檢查方法。
 * 該方法透過 PackageManager.checkSignatures 檢查 Threads 與目標應用 (Instagram) 簽名是否一致。
 * 當 Instagram 使用 Morphe 簽名而 Threads 簽名不同時，此處會拋出 SecurityException。
 * 透過 returnEarly(true) 可繞過簽名一致性檢查，使 Intent 順利綁定目標元件。
 */
internal object FamilySignatureVerifyFingerprint : Fingerprint(
    returnType = "Z",
    parameters = listOf("Landroid/content/Context;", "Landroid/content/pm/ComponentInfo;"),
    strings = listOf("Error verifying the signature for ")
)
