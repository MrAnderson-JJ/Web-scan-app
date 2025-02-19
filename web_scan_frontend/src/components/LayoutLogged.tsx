import { useEffect, useState } from "react";
import { useNavigate, Outlet } from "react-router-dom";
import Loader from "../components/loader/Loader";
import UserLayout from "../components/Layout";
import { loginPath } from "../routes/routePaths";

const ProtectedRoute = () => {
  const navigate = useNavigate();
  const [checkingAuth, setCheckingAuth] = useState(true);

  const isAuthenticated = true;

  useEffect(() => {
    if (!isAuthenticated) {
      navigate(loginPath, { replace: true });
    } else {
      setCheckingAuth(false);
    }
  }, [isAuthenticated, navigate]);

  if (checkingAuth) {
    return <Loader />;
  }

  return (
    <UserLayout>
      <Outlet />
    </UserLayout>
  );
};

export default ProtectedRoute;
