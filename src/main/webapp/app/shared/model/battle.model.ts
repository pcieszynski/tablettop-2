import { IMonster } from 'app/shared/model//monster.model';
import { IEvent } from 'app/shared/model//event.model';

export interface IBattle {
  id?: number;
  monster?: string;
  monsterNumber?: number;
  monsters?: IMonster[];
  event?: IEvent;
}

export const defaultValue: Readonly<IBattle> = {};
