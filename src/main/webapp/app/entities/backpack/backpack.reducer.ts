import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBackpack, defaultValue } from 'app/shared/model/backpack.model';

export const ACTION_TYPES = {
  FETCH_BACKPACK_LIST: 'backpack/FETCH_BACKPACK_LIST',
  FETCH_BACKPACK: 'backpack/FETCH_BACKPACK',
  CREATE_BACKPACK: 'backpack/CREATE_BACKPACK',
  UPDATE_BACKPACK: 'backpack/UPDATE_BACKPACK',
  DELETE_BACKPACK: 'backpack/DELETE_BACKPACK',
  RESET: 'backpack/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBackpack>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type BackpackState = Readonly<typeof initialState>;

// Reducer

export default (state: BackpackState = initialState, action): BackpackState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BACKPACK_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BACKPACK):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_BACKPACK):
    case REQUEST(ACTION_TYPES.UPDATE_BACKPACK):
    case REQUEST(ACTION_TYPES.DELETE_BACKPACK):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_BACKPACK_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BACKPACK):
    case FAILURE(ACTION_TYPES.CREATE_BACKPACK):
    case FAILURE(ACTION_TYPES.UPDATE_BACKPACK):
    case FAILURE(ACTION_TYPES.DELETE_BACKPACK):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_BACKPACK_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_BACKPACK):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_BACKPACK):
    case SUCCESS(ACTION_TYPES.UPDATE_BACKPACK):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_BACKPACK):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/backpacks';

// Actions

export const getEntities: ICrudGetAllAction<IBackpack> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_BACKPACK_LIST,
  payload: axios.get<IBackpack>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IBackpack> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BACKPACK,
    payload: axios.get<IBackpack>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IBackpack> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BACKPACK,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBackpack> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BACKPACK,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBackpack> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BACKPACK,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
