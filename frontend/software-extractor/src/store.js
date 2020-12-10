import {configureStore} from '@reduxjs/toolkit';
import entityReducer from './reducers/entityReducer' 

export default configureStore({
  reducer: {
    entity: entityReducer
  }
})
