import { ICharacter } from 'app/shared/model//character.model';
import { IEvent } from 'app/shared/model//event.model';
import { IPlayer } from 'app/shared/model//player.model';
import { IGamemaster } from 'app/shared/model//gamemaster.model';

export interface IGame {
  id?: number;
  currentEvent?: number;
  currentPlayer?: string;
  characters?: ICharacter[];
  events?: IEvent[];
  players?: IPlayer[];
  gamemaster?: IGamemaster;
}

export const defaultValue: Readonly<IGame> = {};
