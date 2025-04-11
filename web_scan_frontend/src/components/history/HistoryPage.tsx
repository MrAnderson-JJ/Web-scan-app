import { useEffect, useState } from "react";
import { fetchUserScans, fetchFilteredScans } from "@/api/userApi";
import { useKeycloak } from "@react-keycloak/web";
import { FilterScansDto, UserScan } from "@/dtos";
import UserScansTable from "./UserScansTable";
import FilterUserScansTableForm from "./FilterUserScansTableForm";

const HistoryPage = () => {
  const { keycloak } = useKeycloak();
  const [userScans, setUserScans] = useState<UserScan[]>([]);

  useEffect(() => {
    const loadUserScans = async () => {
      try {
        if (keycloak.subject) {
          const data = await fetchUserScans(keycloak.subject);
          setUserScans(data);
        }
      } catch (error) {
        console.error("Failed to load user scans.");
      }
    };
    loadUserScans();
  }, []);

  const handleSubmit = async (result: FilterScansDto) => {
    const filteredScans = await fetchFilteredScans(result);
    setUserScans(filteredScans);
  };

  return (
    <div className="max-w-full mx-auto px-4 py-8">
      <h1 className="text-3xl font-bold mb-6 text-center">Your Scan History</h1>

      <div className="mb-8 bg-white p-6 rounded-2xl shadow">
        <h2 className="text-xl font-semibold mb-4">Filter Your Scans</h2>
        <FilterUserScansTableForm onFilter={handleSubmit} />
      </div>

      <div className="bg-white p-6 rounded-2xl shadow">
        <h2 className="text-xl font-semibold mb-4">Scans</h2>
        {userScans.length > 0 ? (
          <UserScansTable userScans={userScans} />
        ) : (
          <p className="text-gray-600">No scans found.</p>
        )}
      </div>
    </div>
  );
};

export default HistoryPage;

