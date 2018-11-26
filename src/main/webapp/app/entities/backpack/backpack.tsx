import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './backpack.reducer';
import { IBackpack } from 'app/shared/model/backpack.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBackpackProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class Backpack extends React.Component<IBackpackProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { backpackList, match } = this.props;
    return (
      <div>
        <h2 id="backpack-heading">
          Backpacks
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Backpack
          </Link>
        </h2>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Character</th>
                <th>Legs</th>
                <th>Boots</th>
                <th>Gloves</th>
                <th>Righthand</th>
                <th>Lefthand</th>
                <th>Armour</th>
                <th>Helmet</th>
                <th>Item</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {backpackList.map((backpack, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${backpack.id}`} color="link" size="sm">
                      {backpack.id}
                    </Button>
                  </td>
                  <td>{backpack.character ? <Link to={`character/${backpack.character.id}`}>{backpack.character.id}</Link> : ''}</td>
                  <td>
                    {backpack.legs
                      ? backpack.legs.map((val, j) => (
                          <span key={j}>
                            <Link to={`legs/${val.id}`}>{val.id}</Link>
                            {j === backpack.legs.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {backpack.boots
                      ? backpack.boots.map((val, j) => (
                          <span key={j}>
                            <Link to={`boots/${val.id}`}>{val.id}</Link>
                            {j === backpack.boots.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {backpack.gloves
                      ? backpack.gloves.map((val, j) => (
                          <span key={j}>
                            <Link to={`gloves/${val.id}`}>{val.id}</Link>
                            {j === backpack.gloves.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {backpack.righthands
                      ? backpack.righthands.map((val, j) => (
                          <span key={j}>
                            <Link to={`right-hand/${val.id}`}>{val.id}</Link>
                            {j === backpack.righthands.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {backpack.lefthands
                      ? backpack.lefthands.map((val, j) => (
                          <span key={j}>
                            <Link to={`left-hand/${val.id}`}>{val.id}</Link>
                            {j === backpack.lefthands.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {backpack.armours
                      ? backpack.armours.map((val, j) => (
                          <span key={j}>
                            <Link to={`armour/${val.id}`}>{val.id}</Link>
                            {j === backpack.armours.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {backpack.helmets
                      ? backpack.helmets.map((val, j) => (
                          <span key={j}>
                            <Link to={`helmet/${val.id}`}>{val.id}</Link>
                            {j === backpack.helmets.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {backpack.items
                      ? backpack.items.map((val, j) => (
                          <span key={j}>
                            <Link to={`item/${val.id}`}>{val.id}</Link>
                            {j === backpack.items.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${backpack.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${backpack.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${backpack.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ backpack }: IRootState) => ({
  backpackList: backpack.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(Backpack);
