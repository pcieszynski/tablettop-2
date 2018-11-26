import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Backpack from './backpack';
import BackpackDetail from './backpack-detail';
import BackpackUpdate from './backpack-update';
import BackpackDeleteDialog from './backpack-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BackpackUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BackpackUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BackpackDetail} />
      <ErrorBoundaryRoute path={match.url} component={Backpack} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={BackpackDeleteDialog} />
  </>
);

export default Routes;
