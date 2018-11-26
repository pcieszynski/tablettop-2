import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICharacter } from 'app/shared/model/character.model';
import { getEntities as getCharacters } from 'app/entities/character/character.reducer';
import { ILegs } from 'app/shared/model/legs.model';
import { getEntities as getLegs } from 'app/entities/legs/legs.reducer';
import { IBoots } from 'app/shared/model/boots.model';
import { getEntities as getBoots } from 'app/entities/boots/boots.reducer';
import { IGloves } from 'app/shared/model/gloves.model';
import { getEntities as getGloves } from 'app/entities/gloves/gloves.reducer';
import { IRightHand } from 'app/shared/model/right-hand.model';
import { getEntities as getRightHands } from 'app/entities/right-hand/right-hand.reducer';
import { ILeftHand } from 'app/shared/model/left-hand.model';
import { getEntities as getLeftHands } from 'app/entities/left-hand/left-hand.reducer';
import { IArmour } from 'app/shared/model/armour.model';
import { getEntities as getArmours } from 'app/entities/armour/armour.reducer';
import { IHelmet } from 'app/shared/model/helmet.model';
import { getEntities as getHelmets } from 'app/entities/helmet/helmet.reducer';
import { IItem } from 'app/shared/model/item.model';
import { getEntities as getItems } from 'app/entities/item/item.reducer';
import { getEntity, updateEntity, createEntity, reset } from './backpack.reducer';
import { IBackpack } from 'app/shared/model/backpack.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBackpackUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IBackpackUpdateState {
  isNew: boolean;
  idslegs: any[];
  idsboots: any[];
  idsgloves: any[];
  idsrighthand: any[];
  idslefthand: any[];
  idsarmour: any[];
  idshelmet: any[];
  idsitem: any[];
  characterId: string;
}

export class BackpackUpdate extends React.Component<IBackpackUpdateProps, IBackpackUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      idslegs: [],
      idsboots: [],
      idsgloves: [],
      idsrighthand: [],
      idslefthand: [],
      idsarmour: [],
      idshelmet: [],
      idsitem: [],
      characterId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getCharacters();
    this.props.getLegs();
    this.props.getBoots();
    this.props.getGloves();
    this.props.getRightHands();
    this.props.getLeftHands();
    this.props.getArmours();
    this.props.getHelmets();
    this.props.getItems();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { backpackEntity } = this.props;
      const entity = {
        ...backpackEntity,
        ...values,
        legs: mapIdList(values.legs),
        boots: mapIdList(values.boots),
        gloves: mapIdList(values.gloves),
        righthands: mapIdList(values.righthands),
        lefthands: mapIdList(values.lefthands),
        armours: mapIdList(values.armours),
        helmets: mapIdList(values.helmets),
        items: mapIdList(values.items)
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/backpack');
  };

  render() {
    const {
      backpackEntity,
      characters,
      legs,
      boots,
      gloves,
      rightHands,
      leftHands,
      armours,
      helmets,
      items,
      loading,
      updating
    } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="tabletTopApp.backpack.home.createOrEditLabel">Create or edit a Backpack</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : backpackEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="backpack-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label for="character.id">Character</Label>
                  <AvInput id="backpack-character" type="select" className="form-control" name="character.id">
                    <option value="" key="0" />
                    {characters
                      ? characters.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="legs">Legs</Label>
                  <AvInput
                    id="backpack-legs"
                    type="select"
                    multiple
                    className="form-control"
                    name="legs"
                    value={backpackEntity.legs && backpackEntity.legs.map(e => e.id)}
                  >
                    <option value="" key="0" />
                    {legs
                      ? legs.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="boots">Boots</Label>
                  <AvInput
                    id="backpack-boots"
                    type="select"
                    multiple
                    className="form-control"
                    name="boots"
                    value={backpackEntity.boots && backpackEntity.boots.map(e => e.id)}
                  >
                    <option value="" key="0" />
                    {boots
                      ? boots.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="gloves">Gloves</Label>
                  <AvInput
                    id="backpack-gloves"
                    type="select"
                    multiple
                    className="form-control"
                    name="gloves"
                    value={backpackEntity.gloves && backpackEntity.gloves.map(e => e.id)}
                  >
                    <option value="" key="0" />
                    {gloves
                      ? gloves.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="rightHands">Righthand</Label>
                  <AvInput
                    id="backpack-righthand"
                    type="select"
                    multiple
                    className="form-control"
                    name="righthands"
                    value={backpackEntity.righthands && backpackEntity.righthands.map(e => e.id)}
                  >
                    <option value="" key="0" />
                    {rightHands
                      ? rightHands.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="leftHands">Lefthand</Label>
                  <AvInput
                    id="backpack-lefthand"
                    type="select"
                    multiple
                    className="form-control"
                    name="lefthands"
                    value={backpackEntity.lefthands && backpackEntity.lefthands.map(e => e.id)}
                  >
                    <option value="" key="0" />
                    {leftHands
                      ? leftHands.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="armours">Armour</Label>
                  <AvInput
                    id="backpack-armour"
                    type="select"
                    multiple
                    className="form-control"
                    name="armours"
                    value={backpackEntity.armours && backpackEntity.armours.map(e => e.id)}
                  >
                    <option value="" key="0" />
                    {armours
                      ? armours.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="helmets">Helmet</Label>
                  <AvInput
                    id="backpack-helmet"
                    type="select"
                    multiple
                    className="form-control"
                    name="helmets"
                    value={backpackEntity.helmets && backpackEntity.helmets.map(e => e.id)}
                  >
                    <option value="" key="0" />
                    {helmets
                      ? helmets.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="items">Item</Label>
                  <AvInput
                    id="backpack-item"
                    type="select"
                    multiple
                    className="form-control"
                    name="items"
                    value={backpackEntity.items && backpackEntity.items.map(e => e.id)}
                  >
                    <option value="" key="0" />
                    {items
                      ? items.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/backpack" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  characters: storeState.character.entities,
  legs: storeState.legs.entities,
  boots: storeState.boots.entities,
  gloves: storeState.gloves.entities,
  rightHands: storeState.rightHand.entities,
  leftHands: storeState.leftHand.entities,
  armours: storeState.armour.entities,
  helmets: storeState.helmet.entities,
  items: storeState.item.entities,
  backpackEntity: storeState.backpack.entity,
  loading: storeState.backpack.loading,
  updating: storeState.backpack.updating,
  updateSuccess: storeState.backpack.updateSuccess
});

const mapDispatchToProps = {
  getCharacters,
  getLegs,
  getBoots,
  getGloves,
  getRightHands,
  getLeftHands,
  getArmours,
  getHelmets,
  getItems,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BackpackUpdate);
