import React, { useEffect, useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import {Container,Paper,Button} from '@mui/material';
import axios from "axios";

export default function Employee() {
    const paperStyle={padding:'50px 20px', width:600,margin:"20px auto"}
    const[firstName,setFirstName]=useState('')
    const[lastName,setLastName]=useState('')
    const[departmentId,setDepartmentId] = useState('')
    const[employees,setEmployees]=useState([])
    const baseURL = "http://localhost:8080/api/employee";


    useEffect(() => {
      const getNotes = () => {
        axios.get(baseURL).then((response) => {
          setEmployees(response.data);
        });
      };
      getNotes();
    }, []);
  
    const handleSubmit = (e) => {
      e.preventDefault();
      if (firstName === "" || lastName === "" || departmentId === "") {
        alert("Error!", "please fill the fields!", "error");
      } else {
        addNotes(firstName, lastName,departmentId);
      }
    };
  
    const addNotes = (firstName, lastName) => {
      axios
        .post(baseURL, {
          firstName: firstName,
          lastName: lastName,
          departmentId:departmentId
        })
        .then((response) => {
          setEmployees([response.data, ...employees]);
        });
      setFirstName("");
      setLastName("");
      setDepartmentId("");
    };
  
    const deleteNote = (employeeId) => {
      axios.delete(`${baseURL}${employeeId}`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
        },
      });
      setEmployees(
        employees.filter((employee) => {
          return employee.employeeId !== employeeId;
        })
      );
    };
  
  return (
    <Container>
        <Paper elevation = {3} style = {paperStyle}>
        <h1 style={{color:"blue"}}>Add Employee</h1>

    <Box
      component="form"
      sx={{
        '& > :not(style)': { m: 1, width: '25ch' },
      }}
      noValidate
      autoComplete="off"
    >
      <TextField id="outlined-basic" label="Employee FirstName" variant="outlined" fullWidth 
        value={firstName}
        onChange={(e)=>setFirstName(e.target.value)}
        />  <br/>
      <TextField id="outlined-basic" label="Employee LastName" variant="outlined" fullWidth
      value={lastName}
      onChange={(e)=>setLastName(e.target.value)}
       /> <br/>
       <TextField id="outlined-basic" label="Department Id" variant="outlined" fullWidth
      value={departmentId}
      onChange={(e)=>setDepartmentId(e.target.value)}
       /><br/>

<Button variant="contained" color="secondary" onClick={handleSubmit}>
  Submit
</Button>
 
    </Box>
    </Paper>

    <h1>Students</h1>

    <Paper elevation={3} style={paperStyle}>

      {employees.map(employee=>(
        <Paper elevation={6} style={{margin:"10px",padding:"15px", textAlign:"left"}} key={employee.employeeId}>
        Name:{employee.firstName}<br/>
        Address:{employee.lastName}<br/>
        Department ID :{employee.department.departmentId} <br/>
        Department Name : {employee.department.name}
        </Paper>
      ))
}
    </Paper>

    </Container>
  );
}
