package app.morphe.patches.threads.fingerprints

import app.morphe.patcher.Fingerprint

/**
 * 匹配 Meta FamilyIntentScope (如 LX/2Sh;) 中的目標應用白名單過濾方法。
 * 該方法驗證目標應用簽名是否在 Meta 官方公鑰白名單中 (7tz.A02)。
 * 透過 returnEarly(true) 可使所有符合 Intent 結構的 Instagram 元件均被視為合法應用。
 */
internal object FamilyAppFilterFingerprint : Fingerprint(
    returnType = "Z",
    parameters = listOf("Landroid/content/Context;", "Landroid/content/pm/ApplicationInfo;"),
    strings = listOf(" is not an app matching the targeted app filter, but fail-open.")
)
