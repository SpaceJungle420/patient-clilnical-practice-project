import React from "react";
import axios from "axios";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

function CollectClinicals() {
  const [patientData, setPatientData] = useState({
    firstName: "",
    lastName: "",
    age: "",
  });
  const { patientId } = useParams();

  const [componentName, setComponentName] = useState("");
  const [componentValue, setComponentValue] = useState("");

  const [isLoading, setLoading] = useState(true);

  useEffect(() => {
    axios
      .get("http://localhost:8080/clinicalservices/api/patients/" + patientId)
      .then((res) => {
        setPatientData(res.data);
        setLoading(false);
      });
  });

  const handleSubmit = (event: any) => {};

  return (
    <div>
      <h2>Patient Details:</h2>
      First Name: {!isLoading ? patientData.firstName : ""}
      <br />
      Last Name: {!isLoading ? patientData.lastName : ""}
      <br />
      Age: {!isLoading ? patientData.age : ""}
      <h2>Patient Clinical Data:</h2>
      <form>
        Clinical Entry Type:
        <select onChange={(e) => setComponentName(e.target.value)}>
          <option>Select One</option>
          <option value="bp">Blood Pressure(Sys/Dys)</option>
          <option value="hw">Height/Weight</option>
          <option value="heartrate">Heart Rate</option>
        </select>
        <br />
        Value:
        <input
          type="text"
          name="componentValue"
          onChange={(e) => setComponentValue(e.target.value)}
        />
        <br />
        <button onClick={handleSubmit}>Confirm</button>
      </form>
    </div>
  );
}

export default CollectClinicals;
