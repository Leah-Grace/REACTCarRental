import './App.css';
import { BrowserRouter } from 'react-router-dom'
import AppRouter from './components/Routers/AppRouter';

function App() {
  return (
    <BrowserRouter>
    {/* Main Router */}
    {/* Home page */}
    {/* Customer pages */}
    {/* Employee pages */}
    <AppRouter />
    </BrowserRouter>
  );
}

export default App;