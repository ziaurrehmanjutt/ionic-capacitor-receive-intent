export interface IntentReceievedPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}