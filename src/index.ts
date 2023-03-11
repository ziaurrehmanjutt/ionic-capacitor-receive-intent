import { registerPlugin } from '@capacitor/core';

import type { IntentReceivedPlugin } from './definitions';

const IntentReceived = registerPlugin<IntentReceivedPlugin>('IntentReceived', {
  // web: () => import('./web').then(m => new m.IntentReceivedWeb()),
});

export * from './definitions';
export { IntentReceived };


