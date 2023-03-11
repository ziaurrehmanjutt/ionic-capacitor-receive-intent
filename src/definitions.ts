import { PluginListenerHandle } from "@capacitor/core";
export interface IntentReceivedPlugin {
  /**
  * Listen for send action intent events (Android only). The extras will be passed as a key value pair
  * directly from the Android intent.
  */
  addListener(eventName: 'newIntentReceived', listenerFunc: (data: AppSendActionIntentResult) => void): Promise<PluginListenerHandle> & PluginListenerHandle;
}
export interface AppSendActionIntentResult {
  /**
   * An object with keys for Android intent names (like 'android.intent.extra.SUBJECT') and their value passed from the Android intent
   */
  extras: any;
};