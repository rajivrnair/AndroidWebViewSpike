Simple project to explore the capabilities of a WebView in Android.

**TODO:**
1. _WebSockets support. (DONE)_
2. _Load Lottie animation. (DONE)_
3. Show location on map.
4. Camera for barcode scanner.
5. Fingerprint Auth.

To run:
- Use Android Studio (or)
- `./gradlew installDebug` from the directory root.

Websockets code is based on http://foretribe.blogspot.com/2013/08/how-to-make-android-webview-support.html which in
turn uses the WebSockets code written by the PhoneGap folks. Minor customisations done.

Remember to close the socket once you're done!
