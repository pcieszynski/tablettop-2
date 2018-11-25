import { INpc } from 'app/shared/model//npc.model';
import { IBattle } from 'app/shared/model//battle.model';
import { IPlayerMessage } from 'app/shared/model//player-message.model';
import { IGame } from 'app/shared/model//game.model';
import { IShop } from 'app/shared/model//shop.model';

export interface IEvent {
  id?: number;
  name?: string;
  description?: any;
  npcs?: INpc[];
  battles?: IBattle[];
  playerMessages?: IPlayerMessage[];
  game?: IGame;
  shop?: IShop;
}

export const defaultValue: Readonly<IEvent> = {};
