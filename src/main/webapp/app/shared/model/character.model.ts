import { ISkill } from 'app/shared/model//skill.model';
import { IGame } from 'app/shared/model//game.model';
import { IStatus } from 'app/shared/model//status.model';
import { IItem } from 'app/shared/model//item.model';
import { IProfession } from 'app/shared/model//profession.model';
import { IPlayer } from 'app/shared/model//player.model';
import { IHelmet } from 'app/shared/model//helmet.model';
import { IArmour } from 'app/shared/model//armour.model';
import { IBoots } from 'app/shared/model//boots.model';
import { IGloves } from 'app/shared/model//gloves.model';
import { ILegs } from 'app/shared/model//legs.model';
import { IRightHand } from 'app/shared/model//right-hand.model';
import { ILeftHand } from 'app/shared/model//left-hand.model';

export interface ICharacter {
  id?: number;
  name?: string;
  level?: number;
  experience?: number;
  maxHitpoints?: number;
  currentHitpoints?: number;
  gold?: number;
  strength?: number;
  agility?: number;
  constituition?: number;
  intelligence?: number;
  willpower?: number;
  charisma?: number;
  skills?: ISkill[];
  games?: IGame[];
  statuses?: IStatus[];
  items?: IItem[];
  profession?: IProfession;
  player?: IPlayer;
  helmet?: IHelmet;
  armour?: IArmour;
  boots?: IBoots;
  gloves?: IGloves;
  legs?: ILegs;
  rightHand?: IRightHand;
  leftHand?: ILeftHand;
}

export const defaultValue: Readonly<ICharacter> = {};
