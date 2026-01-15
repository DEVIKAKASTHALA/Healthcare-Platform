import { configureStore } from '@reduxjs/toolkit';
import patientReducer from './patientSlice';

export default configureStore({
  reducer: {
    patients: patientReducer
  }
});