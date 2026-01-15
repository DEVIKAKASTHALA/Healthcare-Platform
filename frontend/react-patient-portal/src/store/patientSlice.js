import { createSlice } from '@reduxjs/toolkit';

const patientSlice = createSlice({
  name: 'patients',
  initialState: {
    list: [],
    loading: false,
    error: null
  },
  reducers: {
    setPatients: (state, action) => {
      state.list = action.payload;
    },
    setLoading: (state, action) => {
      state.loading = action.payload;
    },
    setError: (state, action) => {
      state.error = action.payload;
    }
  }
});

export const { setPatients, setLoading, setError } = patientSlice.actions;
export default patientSlice.reducer;