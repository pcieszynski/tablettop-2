import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './backpack.reducer';
import { IBackpack } from 'app/shared/model/backpack.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBackpackDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class BackpackDetail extends React.Component<IBackpackDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { backpackEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Backpack [<b>{backpackEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>Character</dt>
            <dd>{backpackEntity.character ? backpackEntity.character.id : ''}</dd>
            <dt>Legs</dt>
            <dd>
              {backpackEntity.legs
                ? backpackEntity.legs.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.id}</a>
                      {i === backpackEntity.legs.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
            <dt>Boots</dt>
            <dd>
              {backpackEntity.boots
                ? backpackEntity.boots.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.id}</a>
                      {i === backpackEntity.boots.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
            <dt>Gloves</dt>
            <dd>
              {backpackEntity.gloves
                ? backpackEntity.gloves.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.id}</a>
                      {i === backpackEntity.gloves.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
            <dt>Righthand</dt>
            <dd>
              {backpackEntity.righthands
                ? backpackEntity.righthands.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.id}</a>
                      {i === backpackEntity.righthands.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
            <dt>Lefthand</dt>
            <dd>
              {backpackEntity.lefthands
                ? backpackEntity.lefthands.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.id}</a>
                      {i === backpackEntity.lefthands.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
            <dt>Armour</dt>
            <dd>
              {backpackEntity.armours
                ? backpackEntity.armours.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.id}</a>
                      {i === backpackEntity.armours.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
            <dt>Helmet</dt>
            <dd>
              {backpackEntity.helmets
                ? backpackEntity.helmets.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.id}</a>
                      {i === backpackEntity.helmets.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
            <dt>Item</dt>
            <dd>
              {backpackEntity.items
                ? backpackEntity.items.map((val, i) => (
                    <span key={val.id}>
                      <a>{val.id}</a>
                      {i === backpackEntity.items.length - 1 ? '' : ', '}
                    </span>
                  ))
                : null}
            </dd>
          </dl>
          <Button tag={Link} to="/entity/backpack" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/backpack/${backpackEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ backpack }: IRootState) => ({
  backpackEntity: backpack.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BackpackDetail);
