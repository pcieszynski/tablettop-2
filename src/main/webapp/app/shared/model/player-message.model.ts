import { IPlayer } from 'app/shared/model//player.model';
import { IEvent } from 'app/shared/model//event.model';

export interface IPlayerMessage {
  id?: number;
  message?: any;
  attack?: string;
  heal?: number;
  difficulty?: number;
  success?: boolean;
  attribute?: string;
  player?: IPlayer;
  event?: IEvent;
}

export const defaultValue: Readonly<IPlayerMessage> = {
  success: false
};
