import { IMonsterType } from 'app/shared/model//monster-type.model';
import { IBattle } from 'app/shared/model//battle.model';

export interface IMonster {
  id?: number;
  currentHP?: number;
  type?: IMonsterType;
  battles?: IBattle[];
}

export const defaultValue: Readonly<IMonster> = {};
