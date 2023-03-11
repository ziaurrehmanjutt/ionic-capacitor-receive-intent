import { WebPlugin } from '@capacitor/core';

import type { IntentReceievedPlugin } from './definitions';

export class IntentReceievedWeb extends WebPlugin implements IntentReceievedPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
