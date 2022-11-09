import logo from './logo.svg';
import './App.css';
import AppBar from './components/Appbar';
import Employee from './components/Employee';
import { Routes, Route } from "react-router-dom";
import Department from './components/Department';



function App() {
  return (
    <div className="App">
      <AppBar/>
			<Routes>
				<Route exact path="/department" element={<Department />} />
				<Route exact path="/" element={<Employee />} />
			</Routes>
		</div>

  );
}

export default App;
