import { IShop } from 'app/shared/model//shop.model';
import { ICharacter } from 'app/shared/model//character.model';
import { IBackpack } from 'app/shared/model//backpack.model';

export interface IItem {
  id?: number;
  name?: string;
  effect?: string;
  price?: number;
  shops?: IShop[];
  characters?: ICharacter[];
  backpacks?: IBackpack[];
}

export const defaultValue: Readonly<IItem> = {};
