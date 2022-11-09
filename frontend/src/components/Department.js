import React, { useEffect, useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import {Container,Paper,Button} from '@mui/material';
import axios from "axios";

export default function Employee() {
    const paperStyle={padding:'50px 20px', width:600,margin:"20px auto"}
    const[name,setName]=useState('')
    const[departments,setDepartments]=useState([])
    const baseURL = "http://localhost:8080/api/department";


    useEffect(() => {
      const getNotes = () => {
        axios.get(baseURL).then((response) => {
          setDepartments(response.data);
        });
      };
      getNotes();
    }, []);
  
    const handleSubmit = (e) => {
      e.preventDefault();
      if (name === "" ) {
        alert("Error!", "please fill the fields!", "error");
      } else {
        addNotes(name);
      }
    };
  
    const addNotes = (name) => {
      axios
        .post(baseURL, {
          name: name,
        })
        .then((response) => {
          setDepartments([response.data, ...departments]);
        });
      setName("");
    };
  
    const deleteNote = (departmentId) => {
      axios.delete(`${baseURL}${departmentId}`, {
        headers: {
          "Access-Control-Allow-Origin": "*",
        },
      });
      setDepartments(
        departments.filter((department) => {
          return department.departmentId !== departmentId;
        })
      );
    };
  
  return (
    <Container>
        <Paper elevation = {3} style = {paperStyle}>
        <h1 style={{color:"blue"}}>Add Department</h1>

    <Box
      component="form"
      sx={{
        '& > :not(style)': { m: 1, width: '25ch' },
      }}
      noValidate
      autoComplete="off"
    >
      <TextField id="outlined-basic" label="Employee FirstName" variant="outlined" fullWidth 
        value={name}
        onChange={(e)=>setName(e.target.value)}
        /> 
        <br/>
    

<Button variant="contained" color="secondary" onClick={handleSubmit}>
  Submit
</Button>
 
    </Box>
    </Paper>

    <h1>Departments</h1>

    <Paper elevation={3} style={paperStyle}>

      {departments.map(department=>(
        <Paper elevation={6} style={{margin:"10px",padding:"15px", textAlign:"left"}} key={department.departmentId}>
         Department Name : {department.name}<br/>

        </Paper>
      ))
}


    </Paper>

    </Container>
  );
}
