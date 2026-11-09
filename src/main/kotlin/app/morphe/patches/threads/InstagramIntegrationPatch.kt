package app.morphe.patches.threads

import app.morphe.patcher.patch.bytecodePatch
import app.morphe.patcher.patch.Compatibility
import app.morphe.patcher.patch.ApkFileType
import app.morphe.patches.threads.fingerprints.FamilyAppFilterFingerprint
import app.morphe.patches.threads.fingerprints.FamilySignatureVerifyFingerprint
import app.morphe.patches.threads.fingerprints.AppIdentityVerifierFingerprint
import app.morphe.patches.threads.fingerprints.PackageSignatureVerifierFingerprint

@Suppress("unused")
val instagramIntegrationPatch = bytecodePatch(
    name = "Fix Instagram integration",
    description = "修復在安裝非官方/修補版 (Morphe) Instagram 時，Threads 無法開啟 IG 個人檔案連結與無法分享貼文至 IG 限時動態的問題。",
    default = true
) {
    compatibleWith(
        Compatibility(
            name = "Threads",
            packageName = "com.instagram.barcelona",
            apkFileType = ApkFileType.APK
        )
    )

    execute {
        // 核心修復 1: 繞過 FamilyIntentScope 的目標應用白名單過濾
        FamilyAppFilterFingerprint.method.returnEarly(true)

        // 核心修復 2: 繞過 FamilyIntentScope 的跨應用簽名一致性校驗
        FamilySignatureVerifyFingerprint.method.returnEarly(true)

        // 全域防護: 若存在底層簽名校驗與身分校驗器，一併強制通過
        runCatching {
            PackageSignatureVerifierFingerprint.method.returnEarly(true)
        }
        runCatching {
            AppIdentityVerifierFingerprint.method.returnEarly(true)
        }
    }
}
