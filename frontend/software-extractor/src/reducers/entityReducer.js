import {createSlice} from '@reduxjs/toolkit'

export const entitySlice = createSlice({
  name: 'entity',
  initialState: {
    data: {}
  },
  reducers: {
    addAllEntities: (state, action) => {
      state.data = action.payload
    },
  }
})

export const {addAllEntities} = entitySlice.actions;
export const selectEntity = state => state.entity.data;

export default entitySlice.reducer;