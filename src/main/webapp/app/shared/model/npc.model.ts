import { IEvent } from 'app/shared/model//event.model';

export interface INpc {
  id?: number;
  name?: string;
  message?: any;
  event?: IEvent;
}

export const defaultValue: Readonly<INpc> = {};
