import { Route, Routes } from "react-router-dom";
import { Suspense, lazy } from "react";
import {
  history,
  dashboardPath,
  loginPath,
  rootPath
} from "./routePaths"
import LayoutLogged from "@/components/layout/LayoutLogged";
import LayoutUnlogged from "@/components/layout/LayoutUnlogged";

import Loader from "../components/loader/Loader";
/* import ProtectedRoute from "./protectedRoute"; */

// import route paths

const HomeComponent = lazy(() => import("../components/scanDashboard/ScanDashboard"));
const HomeTableComponent = lazy(() => import("../components/home/HomePage"));
const HistoryComponent = lazy(() => import("../components/history/HistoryPage"));
const RouteNotFoundComponent = lazy(() => import("../components/pageNotFound/PageNotFound"));
const LoginComponent = lazy(() => import("../components/login/LoginPage"));

const AllRoutesComponent = () => {
  return (
<Suspense fallback={<Loader />}>
  <Routes>
    <Route path={rootPath} element={<LayoutLogged />}>
      <Route index element={<HomeTableComponent />} />
      <Route path={dashboardPath} element={<HomeComponent />} />
      <Route path={history} element={<HistoryComponent />} />
    </Route>

    <Route element={<LayoutUnlogged />}>
          <Route path={loginPath} element={<LoginComponent />} />
    <Route path="*" element={<RouteNotFoundComponent />} />
    </Route>
    
  </Routes>
</Suspense>
  );
};

export default AllRoutesComponent;