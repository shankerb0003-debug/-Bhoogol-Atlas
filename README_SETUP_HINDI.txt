====================================================================
भूगोल एटलस (Bhoogol Atlas) — मोबाइल से GitHub Actions बिल्ड गाइड
====================================================================
Package Name: com.bhawanishankar.bhoogolatlas
Publisher: Bhawani Shankar (pawanrawat8505@gmail.com | +91 9772743023)

1. ZIP अनज़िप करें:
   - अपने फ़ोन के File Manager / ZArchiver ऐप से इस ZIP को Extract करें।

2. GitHub पर नई Repository बनाएँ:
   - मोबाइल ब्राउज़र में github.com पर "+" -> "New repository" खोलें।
   - नाम रखें: bhoogol-atlas -> "Create repository" दबाएँ।
   - "uploading an existing file" पर क्लिक करके bhoogol_atlas_android के अंदर की
     सारी फ़ाइलें व फ़ोल्डर (.github, app, build.gradle, settings.gradle, gradle.properties)
     अपलोड करके "Commit changes" दबाएँ।

3. AdMob IDs बदलें (वैकल्पिक, बाद में भी कर सकते हैं):
   - GitHub पर app/src/main/res/values/strings.xml खोलें और पेंसिल (Edit) आइकन से
     अपनी असली AdMob App ID, Banner ID और Interstitial ID पेस्ट करके Commit करें।

4. .aab और .apk डाउनलोड करें:
   - GitHub पर ऊपर "Actions" टैब में जाएँ -> "Build Bhoogol Atlas Android" चुनें।
   - बिल्ड हरा (Green ✓) होने पर नीचे "Artifacts" से:
     * BhoogolAtlas-Test-APK (फ़ोन पर टेस्ट करने के लिए)
     * BhoogolAtlas-PlayStore-AAB (Play Store के लिए)
     सीधे अपने फ़ोन में डाउनलोड करें!
====================================================================
