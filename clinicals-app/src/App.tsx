import React from "react";
import "./App.css";
import { Routes, Route } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import AddPatient from "./components/AddPatient";
import AnalyzeData from "./components/AnalyzeData";
import CollectClinicals from "./components/CollectClinicals";
import Home from "./components/Home";

function App() {
  return (
    <div className="App">
      <ToastContainer
        autoClose={2000}
        position={toast.POSITION.BOTTOM_CENTER}
      />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route
          path="/patientDetails/:patientId"
          element={<CollectClinicals />}
        />
        <Route path="/addPatient" element={<AddPatient />} />
        <Route path="/analyze/:patientId" element={<AnalyzeData />} />
      </Routes>
    </div>
  );
}

export default App;
