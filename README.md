# ionic-capacitor-recieve-intent

Its Plugin

## Install

```bash
npm install ionic-capacitor-recieve-intent
npx cap sync
```

## API

<docgen-index>

* [`addListener('newIntentReceived', ...)`](#addlistenernewintentreceived)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### addListener('newIntentReceived', ...)

```typescript
addListener(eventName: 'newIntentReceived', listenerFunc: (data: AppSendActionIntentResult) => void) => Promise<PluginListenerHandle> & PluginListenerHandle
```

Listen for send action intent events (Android only). The extras will be passed as a key value pair
directly from the Android intent.

| Param              | Type                                                                                               |
| ------------------ | -------------------------------------------------------------------------------------------------- |
| **`eventName`**    | <code>'newIntentReceived'</code>                                                                   |
| **`listenerFunc`** | <code>(data: <a href="#appsendactionintentresult">AppSendActionIntentResult</a>) =&gt; void</code> |

**Returns:** <code>Promise&lt;<a href="#pluginlistenerhandle">PluginListenerHandle</a>&gt; & <a href="#pluginlistenerhandle">PluginListenerHandle</a></code>

--------------------


### Interfaces


#### PluginListenerHandle

| Prop         | Type                                      |
| ------------ | ----------------------------------------- |
| **`remove`** | <code>() =&gt; Promise&lt;void&gt;</code> |


#### AppSendActionIntentResult

| Prop         | Type             | Description                                                                                                                       |
| ------------ | ---------------- | --------------------------------------------------------------------------------------------------------------------------------- |
| **`extras`** | <code>any</code> | An object with keys for Android intent names (like 'android.intent.extra.SUBJECT') and their value passed from the Android intent |

</docgen-api>
