import { registerPlugin } from '@capacitor/core';

import type { IntentReceievedPlugin } from './definitions';

const IntentReceieved = registerPlugin<IntentReceievedPlugin>('IntentReceieved', {
  web: () => import('./web').then(m => new m.IntentReceievedWeb()),
});

export * from './definitions';
export { IntentReceieved };
