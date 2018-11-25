import { IGame } from 'app/shared/model//game.model';
import { IPlayer } from 'app/shared/model//player.model';

export interface IGamemaster {
  id?: number;
  games?: IGame[];
  player?: IPlayer;
}

export const defaultValue: Readonly<IGamemaster> = {};
