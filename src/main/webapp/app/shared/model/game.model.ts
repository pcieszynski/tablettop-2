import { IEvent } from 'app/shared/model//event.model';
import { IPlayer } from 'app/shared/model//player.model';
import { IGamemaster } from 'app/shared/model//gamemaster.model';
import { ICharacter } from 'app/shared/model//character.model';

export interface IGame {
  id?: number;
  currentEvent?: number;
  currentPlayer?: string;
  events?: IEvent[];
  players?: IPlayer[];
  gamemaster?: IGamemaster;
  characters?: ICharacter[];
}

export const defaultValue: Readonly<IGame> = {};
