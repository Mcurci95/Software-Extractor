import {createSlice} from '@reduxjs/toolkit'

export const entitySlice = createSlice({
  name: 'entity',
  initialState: {
    data: [],
    diffs: {},
  },
  reducers: {
    addAllEntities: (state, action) => {
      state.data = action.payload
    },
    addAllDiffs: (state, action) => {
      state.diffs = action.payload
    }
  }
})

export const {addAllEntities, addAllDiffs} = entitySlice.actions;

export const selectEntity = state => state.entity.data;
export const selectDiff = state => state.entity.diffs;

export default entitySlice.reducer;