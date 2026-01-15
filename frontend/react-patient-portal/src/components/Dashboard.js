import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Dashboard = () => {
  const [patients, setPatients] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchPatients();
  }, []);

  const fetchPatients = async () => {
    try {
      const response = await axios.get('http://localhost:8081/api/patients');
      setPatients(response.data);
      setLoading(false);
    } catch (error) {
      console.error('Error fetching patients:', error);
      setLoading(false);
    }
  };

  if (loading) return <div className="dashboard">Loading...</div>;

  return (
    <div className="dashboard">
      <h1>Healthcare Dashboard</h1>
      <div className="card">
        <h2>Total Patients: {patients.length}</h2>
      </div>
      <div className="card">
        <h3>Recent Patients</h3>
        {patients.slice(0, 5).map(patient => (
          <div key={patient.patientId} style={{padding: '10px', borderBottom: '1px solid #eee'}}>
            <strong>{patient.firstName} {patient.lastName}</strong>
            <p>Email: {patient.email}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Dashboard;