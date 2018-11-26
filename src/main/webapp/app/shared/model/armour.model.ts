import { ICharacter } from 'app/shared/model//character.model';
import { IShop } from 'app/shared/model//shop.model';
import { IBackpack } from 'app/shared/model//backpack.model';

export interface IArmour {
  id?: number;
  name?: string;
  effect?: string;
  price?: number;
  defence?: number;
  part?: string;
  characters?: ICharacter[];
  shops?: IShop[];
  backpacks?: IBackpack[];
}

export const defaultValue: Readonly<IArmour> = {};
