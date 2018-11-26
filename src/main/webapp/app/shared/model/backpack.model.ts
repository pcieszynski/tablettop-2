import { ICharacter } from 'app/shared/model//character.model';
import { ILegs } from 'app/shared/model//legs.model';
import { IBoots } from 'app/shared/model//boots.model';
import { IGloves } from 'app/shared/model//gloves.model';
import { IRightHand } from 'app/shared/model//right-hand.model';
import { ILeftHand } from 'app/shared/model//left-hand.model';
import { IArmour } from 'app/shared/model//armour.model';
import { IHelmet } from 'app/shared/model//helmet.model';
import { IItem } from 'app/shared/model//item.model';

export interface IBackpack {
  id?: number;
  character?: ICharacter;
  legs?: ILegs[];
  boots?: IBoots[];
  gloves?: IGloves[];
  righthands?: IRightHand[];
  lefthands?: ILeftHand[];
  armours?: IArmour[];
  helmets?: IHelmet[];
  items?: IItem[];
}

export const defaultValue: Readonly<IBackpack> = {};
