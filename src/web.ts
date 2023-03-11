import { WebPlugin } from '@capacitor/core';

import type { IntentReceivedPlugin } from './definitions';

export class IntentReceivedWeb extends WebPlugin implements IntentReceivedPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
